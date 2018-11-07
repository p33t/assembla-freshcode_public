import React from 'react'
import {withRouteData} from 'react-static'
import Product from "./Product";
//

export default withRouteData(() => (
    <div>
        <h1>Usage</h1>
        <p>
            To use <Product/> you need to maintain the ambient tempo (being played) whilst comparing it with the
            desired tempo being shown. In doing so, one can keep a performance going even if the tempo is incorrect.
        </p>
        <p>
            When comparing the ambient tempo with the display, you'll find the 'one' beat lands at a particular point on
            the circle. If this point remains in the same place then you're in time. If this point 'slips' then you're
            out of time. If this point slips clockwise (away from the rotor) then you're slow. If this point slips
            counter-clockwise (toward the rotor) then you're fast.
        </p>
        <p>
            To fix tempo, simply ride the start or end of the beat to gradually fix the 'slipage'.
        </p>
        <p>
            Naturally, this should all be practiced before using <Product/> during a performance.
        </p>
    </div>
))
