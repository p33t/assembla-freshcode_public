import * as React from 'react';
import './App.css';

import logo from './logo.svg';
import Description from './tutorial/Description';
import Header from './tutorial/Header';

class App extends React.Component {
  public render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
          <Header name="REACT" />
          <Header />
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.tsx</code> and save to reload.
        </p>

        <Description countBy={3} />
      </div>
    );
  }
}

export default App;
