import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  NavLink
} from "react-router-dom";
import facade from "./apiFacade";

function Login(props) {
  const [user, setUser] = useState({ username: "", password: "" });

  const login = evt => {
    evt.preventDefault();
    console.log(user);

    props.login(user.username, user.password);
  };

  const onChange = evt => {
    setUser({ ...user, [evt.target.id]: evt.target.value });
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={login} onChange={onChange}>
        <input placeholder="User Name" id="username" />
        <input placeholder="Password" id="password" />
        <button>Login</button>
      </form>
    </div>
  );
}

function LoggedIn() {
  const [data, setDate] = useState({ msg: "Fetching!!" });

  useEffect(() => {
    facade.fetchData().then(res => setDate(res));
  }, []);

  return (
    <div>
      <h2>Data Received from server</h2>
      <h3>{data.msg}</h3>
    </div>
  );
}

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  const logout = () => {
    facade.logout();
    setLoggedIn(false);
  };
  const login = (user, pass) => {
    facade.login(user, pass).then(res => setLoggedIn(true));
  };

  return (
    <Router>
      <Header></Header>
      <Switch>
        <Route path="/login">
          {loggedIn ? (<div><JokesV2 /></div>) : <Login login={login} />}
        </Route>
        <Route path="/jokes">
          <Jokes></Jokes>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;

function Header() {
  return (
    <ul>
      <li>
        <NavLink to="/login">Login</NavLink>
      </li>
      <li>
        <NavLink to="/jokes">Jokes</NavLink>
      </li>
    </ul>
  );
}

function Jokes() {
  const [data, setData] = useState([]);
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    facade
      .fetchJokesByCategory("food,fashion,history")
      .then(res => setData(res.jokes));
    facade.fecthCategories().then(res => setCategories(res));
  }, []);

  const fetchData = cats => {
    facade.fetchJokesByCategory(cats).then(res => setData(res.jokes));
  };

  return (
    <div>
      <table>
        <tr>
          <th>category</th>
          <th>joke</th>
        </tr>
        {data.map((joke, index) => (
          <tr key={index}>
            <td>{joke.category}</td>
            <td>{joke.joke}</td>
          </tr>
        ))}
      </table>
      <CategorySelect data={categories} fetchData={fetchData} count={4}></CategorySelect>
    </div>
  );
}

function JokesV2() {
  const [data, setData] = useState([]);
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    facade
      .fetchJokesByCategoryV2("food,fashion,history")
      .then(res => setData(res.jokes));
    facade.fecthCategories().then(res => setCategories(res));
  }, []);

  const fetchData = cats => {
    facade.fetchJokesByCategoryV2(cats).then(res => setData(res.jokes));
  };

  return (
    <div>
      <table>
        <tr>
          <th>category</th>
          <th>joke</th>
        </tr>
        {data.map((joke, index) => (
          <tr key={index}>
            <td>{joke.category}</td>
            <td>{joke.joke}</td>
          </tr>
        ))}
      </table>
      <CategorySelect data={categories} fetchData={fetchData} count={12}></CategorySelect>
    </div>
  );
}

function CategorySelect({ data, fetchData, count }) {
  const [categories, setCategories] = useState({});

  const onChange = evt => {
    setCategories({ ...categories, [evt.target.id]: evt.target.value });
  };

  const onSubmit = evt => {
    evt.preventDefault();
    fetchData(
      Object.keys(categories)
        .map(function(k) {
          return categories[k];
        })
        .join(",")
    );
  };

  const createSelects = (count, data) => {
    let selects = [];

    for (let i = 1; i <= count; i++) {
      selects.push(
        <select id={"category" + i} onChange={onChange}>
          <option value="">Select...</option>
          {data.map((category) => (
            <option key={category.id} value={category.name}>
              {category.name}
            </option>
          ))}
        </select>
      );
    }

    return selects;
  };

  return (
    <form onSubmit={onSubmit}>
      {createSelects(count, data)}
      <input type="submit"></input>
    </form>
  );
}
