import React from 'react'
import {withRouteData} from 'react-static'
import Product from "./Product";
//

export default withRouteData(() => (
    <div>
        <h1>Usage</h1>
        <p>
            First and foremost, avoid the tendancy to focus on the spinning 'rotor' (it is rather hypnotizing).
            Instead, choose and <u>focus on a single 'indicator'</u> on the circle. This will facilitate the use
            of <Product/> as a simple metronome.
        </p>
        <p>
            To use <Product/> for tempo correction you need to maintain the ambient tempo (being played) whilst
            comparing it with the desired tempo being shown. In doing so, one can keep a performance going even if the
            tempo is incorrect.
        </p>
        <p>
            When comparing the ambient tempo with the display, you'll find the 'one' beat lands on a particular
            indicator on the circle. If this point remains in the same place then you're in time. If this point 'slips'
            then you're out of time. If this point slips clockwise (away from the rotor) then you're slow. If this point
            slips counter-clockwise (toward the rotor) then you're fast.
        </p>
        <p>
            To fix tempo, simply ride the start or end of the beat to gradually reduce and eliminate the 'slipage'.
        </p>
        <p>
            Naturally, this should all be practiced before using <Product/> during a performance.
        </p>

        <h1>Tips</h1>
        <p>
            There are a number of + and - buttons that can be used to adjust the tempo. They are designed
            to minimize the number of clicks.
        </p>
        <p>
            The tempo shown in the center of the circle provides a way to 'train' the metronome to the desired
            beats-per-minute. Just <u>repeatedly tap on the number at the desired rate</u>. This is handy to gauge the tempo of
            a song one is listening to.
        </p>
    </div>
))
