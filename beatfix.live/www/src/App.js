import React from 'react'
import {Router, Link} from 'react-static'
import {hot} from 'react-hot-loader'
import {Helmet} from 'react-helmet'
//
import Routes from 'react-static-routes'

import './app.css'

const App = () => (
    <Router>
        <div>
            <Helmet>
                <title>beatfix.live</title>
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
        </div>
    </Router>
);

export default hot(module)(App)
