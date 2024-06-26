import React from "react";


const ImageSVG = () => {
    return (
        <svg width="362pt" height="444pt"
            viewBox="0.00 0.00 361.83 443.60" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink">
            <g id="graph0" className="graph" transform="scale(1 1) rotate(0) translate(4 439.6)">
                <title>ER</title>
                <polygon fill="white" stroke="transparent" points="-4,4 -4,-439.6 357.83,-439.6 357.83,4 -4,4" />
                <text text-anchor="middle" x="176.91" y="-32" font-family="Helvetica,Arial,sans-serif" font-size="20.00">Entity Relation Diagram</text>
                <text text-anchor="middle" x="176.91" y="-10" font-family="Helvetica,Arial,sans-serif" font-size="20.00">drawn by NEATO</text>
                <g id="node1" className="node">
                    <title>course</title>
                    <polygon fill="none" stroke="black" points="128.35,-296.69 71.35,-296.69 71.35,-260.69 128.35,-260.69 128.35,-296.69" />
                    <text text-anchor="middle" x="99.85" y="-274.99" font-family="Helvetica,Arial,sans-serif" font-size="14.00">course</text>
                </g>
                <g id="node10" className="node">
                    <title>C&#45;I</title>
                    <polygon fill="lightgrey" stroke="lightgrey" points="145.58,-367.85 113.24,-349.85 145.58,-331.85 177.92,-349.85 145.58,-367.85" />
                    <text text-anchor="middle" x="145.58" y="-346.15" font-family="Helvetica,Arial,sans-serif" font-size="14.00">C&#45;I</text>
                </g>
                <g id="edge3" className="edge">
                    <title>course&#45;&#45;C&#45;I</title>
                    <path fill="none" stroke="black" d="M111.62,-297.01C119.56,-309.37 129.86,-325.38 137.02,-336.53" />
                    <text text-anchor="middle" x="120.32" y="-320.57" font-family="Helvetica,Arial,sans-serif" font-size="14.00">n</text>
                </g>
                <g id="node2" className="node">
                    <title>institute</title>
                    <polygon fill="none" stroke="black" points="259.47,-367.33 196.47,-367.33 196.47,-331.33 259.47,-331.33 259.47,-367.33" />
                    <text text-anchor="middle" x="227.97" y="-345.63" font-family="Helvetica,Arial,sans-serif" font-size="14.00">institute</text>
                </g>
                <g id="node5" className="node">
                    <title>name1</title>
                    <ellipse fill="none" stroke="black" cx="267.19" cy="-417.6" rx="33.29" ry="18" />
                    <text text-anchor="middle" x="267.19" y="-413.9" font-family="Helvetica,Arial,sans-serif" font-size="14.00">name</text>
                </g>
                <g id="edge5" className="edge">
                    <title>institute&#45;&#45;name1</title>
                    <path fill="none" stroke="black" d="M238.48,-367.63C244.29,-377.74 251.47,-390.23 257.19,-400.2" />
                </g>
                <g id="node12" className="node">
                    <title>S&#45;I</title>
                    <polygon fill="lightgrey" stroke="lightgrey" points="264.33,-293.69 231.99,-275.69 264.33,-257.69 296.67,-275.69 264.33,-293.69" />
                    <text text-anchor="middle" x="264.33" y="-271.99" font-family="Helvetica,Arial,sans-serif" font-size="14.00">S&#45;I</text>
                </g>
                <g id="edge6" className="edge">
                    <title>institute&#45;&#45;S&#45;I</title>
                    <path fill="none" stroke="black" d="M236.95,-331.13C243.18,-318.52 251.36,-301.96 257.15,-290.23" />
                    <text text-anchor="middle" x="243.05" y="-314.48" font-family="Helvetica,Arial,sans-serif" font-size="14.00">1</text>
                </g>
                <g id="node3" className="node">
                    <title>student</title>
                    <polygon fill="none" stroke="black" points="267.59,-213.41 206.59,-213.41 206.59,-177.41 267.59,-177.41 267.59,-213.41" />
                    <text text-anchor="middle" x="237.09" y="-191.71" font-family="Helvetica,Arial,sans-serif" font-size="14.00">student</text>
                </g>
                <g id="node6" className="node">
                    <title>name2</title>
                    <ellipse fill="none" stroke="black" cx="320.68" cy="-193.31" rx="33.29" ry="18" />
                    <text text-anchor="middle" x="320.68" y="-189.61" font-family="Helvetica,Arial,sans-serif" font-size="14.00">name</text>
                </g>
                <g id="edge9" className="edge">
                    <title>student&#45;&#45;name2</title>
                    <path fill="none" stroke="black" d="M267.74,-194.64C274.17,-194.48 280.98,-194.31 287.5,-194.15" />
                </g>
                <g id="node8" className="node">
                    <title>grade</title>
                    <ellipse fill="none" stroke="black" cx="280.78" cy="-127.59" rx="33.29" ry="18" />
                    <text text-anchor="middle" x="280.78" y="-123.89" font-family="Helvetica,Arial,sans-serif" font-size="14.00">grade</text>
                </g>
                <g id="edge8" className="edge">
                    <title>student&#45;&#45;grade</title>
                    <path fill="none" stroke="black" d="M248.8,-177.24C255.27,-167.19 263.27,-154.78 269.64,-144.88" />
                </g>
                <g id="node9" className="node">
                    <title>number</title>
                    <ellipse fill="none" stroke="black" cx="205.2" cy="-118" rx="40.89" ry="18" />
                    <text text-anchor="middle" x="205.2" y="-114.3" font-family="Helvetica,Arial,sans-serif" font-size="14.00">number</text>
                </g>
                <g id="edge10" className="edge">
                    <title>student&#45;&#45;number</title>
                    <path fill="none" stroke="black" d="M229.54,-177.07C224.42,-164.64 217.68,-148.29 212.6,-135.95" />
                </g>
                <g id="node11" className="node">
                    <title>S&#45;C</title>
                    <polygon fill="lightgrey" stroke="lightgrey" points="154.7,-233.32 116.83,-215.32 154.7,-197.32 192.57,-215.32 154.7,-233.32" />
                    <text text-anchor="middle" x="154.7" y="-211.62" font-family="Helvetica,Arial,sans-serif" font-size="14.00">S&#45;C</text>
                </g>
                <g id="edge11" className="edge">
                    <title>student&#45;&#45;S&#45;C</title>
                    <path fill="none" stroke="black" d="M206.41,-202.83C197.72,-204.93 188.36,-207.19 180.02,-209.2" />
                    <text text-anchor="middle" x="199.72" y="-209.82" font-family="Helvetica,Arial,sans-serif" font-size="14.00">m</text>
                </g>
                <g id="node4" className="node">
                    <title>name0</title>
                    <ellipse fill="none" stroke="black" cx="35.92" cy="-327.96" rx="33.29" ry="18" />
                    <text text-anchor="middle" x="35.92" y="-324.26" font-family="Helvetica,Arial,sans-serif" font-size="14.00">name</text>
                </g>
                <g id="edge1" className="edge">
                    <title>name0&#45;&#45;course</title>
                    <path fill="none" stroke="black" d="M55.1,-313.18C61.72,-308.08 69.22,-302.3 76.15,-296.95" />
                </g>
                <g id="node7" className="node">
                    <title>code</title>
                    <ellipse fill="none" stroke="black" cx="29.9" cy="-236.09" rx="29.8" ry="18" />
                    <text text-anchor="middle" x="29.9" y="-232.39" font-family="Helvetica,Arial,sans-serif" font-size="14.00">code</text>
                </g>
                <g id="edge2" className="edge">
                    <title>code&#45;&#45;course</title>
                    <path fill="none" stroke="black" d="M51.26,-249.1C57.53,-252.92 64.49,-257.16 71.14,-261.2" />
                </g>
                <g id="edge4" className="edge">
                    <title>C&#45;I&#45;&#45;institute</title>
                    <path fill="none" stroke="black" d="M177.67,-349.65C183.81,-349.61 190.24,-349.57 196.37,-349.53" />
                    <text text-anchor="middle" x="183.02" y="-353.39" font-family="Helvetica,Arial,sans-serif" font-size="14.00">1</text>
                </g>
                <g id="edge12" className="edge">
                    <title>S&#45;C&#45;&#45;course</title>
                    <path fill="none" stroke="black" d="M143.6,-228.14C135.58,-237.4 124.65,-250.04 115.71,-260.36" />
                    <text text-anchor="middle" x="125.66" y="-248.05" font-family="Helvetica,Arial,sans-serif" font-size="14.00">n</text>
                </g>
                <g id="edge7" className="edge">
                    <title>S&#45;I&#45;&#45;student</title>
                    <path fill="none" stroke="black" d="M259.08,-260.21C254.57,-246.91 248.05,-227.7 243.28,-213.65" />
                    <text text-anchor="middle" x="247.18" y="-240.73" font-family="Helvetica,Arial,sans-serif" font-size="14.00">n</text>
                </g>
            </g>
        </svg>
    );
}
export default ImageSVG;