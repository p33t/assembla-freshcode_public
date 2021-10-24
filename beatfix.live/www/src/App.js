import React from 'react'
import {Router, Link} from 'react-static'
import {hot} from 'react-hot-loader'
import {Helmet} from 'react-helmet'
import Routes from 'react-static-routes'

import './app.css'

const App = (props) => (
    <Router>
        <table style={{width: `${Math.min(props.innerWidth, 1280)}px`, marginLeft: 'auto', marginRight: 'auto'}}><tbody><tr><td>
            <Helmet>
                <title>beatfix</title>
                <link rel="icon"
                      type="image/png"
                      href="./favicon.png"/>
            </Helmet>
            <nav>
                <Link exact to="/">Home</Link>
                <Link to="/features">Features</Link>
                <Link to="/usage">Usage</Link>
                <Link to="/about">About</Link>
            </nav>
            <div className="content">
                <Routes/>
            </div>
        </td></tr></tbody></table>
    </Router>
);

export default hot(module)(App)
