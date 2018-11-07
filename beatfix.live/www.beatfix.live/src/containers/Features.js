import React from 'react'
import {withRouteData} from 'react-static'
import Product from "./Product";
//

export default withRouteData(() => (
    <div>
        <h2>Features</h2>
        <ul>
            <li>Visual display of tempo (suitable for a dark environment)</li>
            <li>Pick up the beat at any point in a bar (no need to wait for a new bar)</li>
            <li>Provides a visual reference  assess accuracy of an ambient tempo</li>
        </ul>

        <h3>With <Product/>, a drummer can:</h3>
        <ul>
            <li>Check the ambient tempo with an extended glance</li>
            <li>Incrementally correct the ambient tempo</li>
            <li>Monitor his/her precision at a glance</li>
        </ul>
    </div>
))
