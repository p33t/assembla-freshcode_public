import React from 'react'
import Product from './Product'
//

export default () => (
    <div>
        <h1>About</h1>
        <p>
            I've been an amateur drummer for over 10 years and needed a metronome to help me monitor and fix my tempo
            during a performance. Very few metronomes can do this and I've never found one I was entirely happy with.
            (The closest was the Matrix MR600 DLX)
        </p>
        <p>
            Being <a href="http://www.freshcode.biz">a software developer</a> by trade, I thought I'd come up with my
            own metronome. <Product/> is the second generation solution.
            The first was <a href="https://sourceforge.net/projects/multi-phasemetr/">Multi-phase Metronome</a> and it
            worked reasonably well.
        </p>
    </div>
)
