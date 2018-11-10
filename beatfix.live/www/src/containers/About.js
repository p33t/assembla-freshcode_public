import React from 'react'
import Product from './Product'
import Provider from "./Provider";
//

export default () => (
    <div>
        <h1>About</h1>
        <p>
            Hi, my name is Peter, an amateur drummer for over 10 years. I've never used a 'click track' and instead
            rely on a visual metronome when onstage to ensure correct tempo, especially to start a song. A performance
            metronome has a number of different requirements to a conventional metronome. In general, it needs to be
            passive, flexible, quickly adjustable and work in the dark.
        </p>
        <p>
            In my experience, the <i>Matrix MR600 DLX</i> metronome is very good for performance usage although not
            perfect. Also, using the MR600, I found I could monitor and fix my tempo during a performance if necessary.
        </p>
        <p>
            Being a software developer by trade, I thought I'd come up with my own metronome. <Product/> is the second
            generation solution. The first was <a href="https://sourceforge.net/projects/multi-phasemetr/">Multi-phase
            Metronome</a> and it worked reasonably well.
        </p>

        <h2><Product/> is a <Provider/> innovation</h2>
    </div>
)
