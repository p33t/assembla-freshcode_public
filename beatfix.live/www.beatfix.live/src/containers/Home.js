import React from 'react'
import {withSiteData} from 'react-static'
//
import logoImg from '../logo.png'
import screenshotImg from '../screenshot.png'

export default withSiteData(() => (
    <div style={{textAlign: 'center'}}>
        <h1>Welcome to</h1>
        <img src={logoImg} alt="logo" style={{display: 'block', margin: '0 auto'}}/>
        <h3>
            a metronome designed for monitoring and correcting tempo during live musical performance
        </h3>
        <p>
            <a href='http://app.beatfix.live'>
                <img src={screenshotImg} alt="screenshot" style={{display: 'block', margin: '0 auto'}}/>
                <span>Click to launch</span>
            </a>
        </p>
    </div>
))
