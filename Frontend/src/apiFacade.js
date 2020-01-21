const URL = "http://localhost:8080/exam3";
//const URL = "";


function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res.json();
}

function ApiFacade() {
  //Insert utility-methods from a latter step (d) here

  const makeOptions = (method, addToken, body) => {
    var opts = {
      method: method,
      headers: {
        "Content-type": "application/json",
        Accept: "application/json"
      }
    };
    if (addToken && loggedIn()) {
      opts.headers["x-access-token"] = getToken();
    }
    if (body) {
      opts.body = JSON.stringify(body);
    }
    return opts;
  };
  const setToken = token => {
    localStorage.setItem("jwtToken", token);
  };
  const getToken = token => {
    return localStorage.getItem("jwtToken");
  };
  const loggedIn = () => {
    const loggedIn = getToken() != null;
    return loggedIn;
  };
  const logout = () => {
    localStorage.removeItem("jwtToken");
  };

  const login = (user, pass) => {
    const options = makeOptions("POST", true, {
      username: user,
      password: pass
    });
    return fetch(URL + "/api/login", options)
      .then(handleHttpErrors)
      .then(res => {
        setToken(res.token);
      });
  };
  const fetchData = () => {
    const options = makeOptions("GET", true);
    return fetch(URL + "/api/info/user", options).then(handleHttpErrors);
  };

  const fetchMovieInfo = (title) => {
    const options = makeOptions("GET", false);
    return fetch(URL + "/api/movieInfo/"+title, options).then(handleHttpErrors);
  };

  const fetchMovieInfoAll = (title) => {
    const options = makeOptions("GET", true);
    return fetch(URL + "/api/movieInfo/all/"+title, options).then(handleHttpErrors);
  };

  const getAllPersons = () => {
    // all? er stien rigtig
    return fetch(URL + "/api/person/all").then(handleHttpErrors);
  };

  const findPerson = (id) => {
    return fetch(URL + "/api/person/id/" + id).then(handleHttpErrors);
  };

  const createPerson = (person) => {
    return fetch(URL + "/api/person/create", makeOptions("POST", false, person)).then(handleHttpErrors);
  };


  return {
    logout,
    login,
    fetchData,
    getAllPersons,
    findPerson,
    createPerson,
    fetchMovieInfo,
    fetchMovieInfoAll
  };
}

const facade = new ApiFacade();
export default facade;