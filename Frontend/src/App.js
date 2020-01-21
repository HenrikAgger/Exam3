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
          {loggedIn ? (<div><MovieInfoAll /></div>) : <Login login={login} />}
        </Route>
        <Route path="/movieInfo">
          <MovieInfo></MovieInfo>
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
        <NavLink to="/movieInfo">Movie info</NavLink>
      </li>
    </ul>
  );
}

function MovieInfo() {
  const [data, setData] = useState([]);
  const [movietitle, setMovietitle] = useState("");

  const fetchData = (evt) => {
    evt.preventDefault();
    facade.fetchMovieInfo(movietitle).then(res => setData([res]));
  };

  const onChange = evt => {
    setMovietitle(evt.target.value);
  }

  return (
    <div>
      <table>
        <tr>
          <th>Title</th>
          <th>Year</th>
          <th>Plot</th>
          <th>Directors</th>
          <th>Genres</th>
          <th>Cast</th>
          <th>Poster</th>
        </tr>
        {data.map((movie, index) => (
          <tr key={index}>
            <td>{movie.title}</td>
            <td>{movie.year}</td>
            <td>{movie.plot}</td>
            <td>{movie.directors}</td>
            <td>{movie.genres}</td>
            <td>{movie.cast}</td>
            <td><img src={movie.poster} height="100" /></td>
          </tr>
        ))}
      </table>
      <form onSubmit={fetchData}>
        <input onChange={onChange} />
      </form>
    </div>
  );
}

function MovieInfoAll() {
  const [data, setData] = useState([]);
  const [movietitle, setMovietitle] = useState("");

  const fetchData = (evt) => {
    evt.preventDefault();
    facade.fetchMovieInfoAll(movietitle).then(res => setData([res]));
  };

  const onChange = evt => {
    setMovietitle(evt.target.value);
  }

  return (
    <div>
      <table>
        <tr>
          <th>Title</th>
          <th>Year</th>
          <th>Plot</th>
          <th>Directors</th>
          <th>Genres</th>
          <th>Cast</th>
          <th>Poster</th>
          <th>ImdbScore</th>
          <th>ViewerScore</th>
          <th>CriticScore</th>
          <th>MetaScore</th>
        </tr>
        {data.map((movie, index) => (
          <tr key={index}>
            <td>{movie.title}</td>
            <td>{movie.year}</td>
            <td>{movie.plot}</td>
            <td>{movie.directors}</td>
            <td>{movie.genres}</td>
            <td>{movie.cast}</td>
            <td><img src={movie.poster} height="100" /></td>
            <td>{movie.imdbRating}</td>
            <td>{movie.viewerRating}</td>
            <td>{movie.criticRating}</td>
            <td>{movie.metaCritic}</td>
          </tr>
        ))}
      </table>
      <form onSubmit={fetchData}>
        <input onChange={onChange} />
      </form>
    </div>
  );
}