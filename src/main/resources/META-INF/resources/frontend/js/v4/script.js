function tree()
{
    var data, root, treemap, svg,
        i = 0,
        duration = 650,
        // margin = {top: 20, right: 10, bottom: 20, left: 50},
        margin = {top: 0, right: 0, bottom: 80, left: 50},
        width = 960 - 4 - margin.left - margin.right, // fitting in block frame
        height = 800 - 4 - margin.top - margin.bottom, // fitting in block frame
        width_multiplier = 90,
        height_extra_space = 0;
    // update;

    function chart(selection)
    {
        selection.each(function()
        {
            height = height - margin.top - margin.bottom;
            width = width - margin.left - margin.right;
            // append the svg object to the selection
            svg = selection.append('svg')
                .attr('width', width + margin.left + margin.right)
                .attr('height', height + margin.top + margin.bottom)
                .append('g')
                .attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');

            // declares a tree layout and assigns the size of the tree
            treemap = d3.tree().size([height, width]);

            // assign parent, children, height, depth
            root = d3.hierarchy(data, function(d) { return d.children });
            root.x0 = (height / 2); // left edge of the rectangle
            root.y0 = 0; // top edge of the triangle

            // collapse after the second level
            root.children.forEach(collapse);

            update(root);

            function collapse(d)
            {
                if (d.children)
                {
                    d._children = d.children;
                    d._children.forEach(collapse);
                    d.children = null;
                }
            }
        });
    }

    chart.width = function(value)
    {
        if (!arguments.length) return width;
        width = value;
        return chart;
    };

    chart.height = function(value)
    {
        if (!arguments.length) return height;
        height = value;
        return chart;
    };

    chart.margin = function(value)
    {
        if (!arguments.length) return margin;
        margin = value;
        return chart;
    };

    chart.data = function(value)
    {
        if (!arguments.length) return data;
        data = value;
        if (typeof updateData === 'function') updateData();
        return chart;
    };

    chart.expandTree = function(value)
    {
        if (!(typeof root === 'undefined'))
        {
            root.children.forEach(expand);
            update(root);

            function expand(d)
            {
                if (d._children)
                {
                    d.children = d._children;
                    d.children.forEach(expand);
                    d._children = null;
                }
            }
        }
    };

    // collapse the node and all it's children
    chart.collapse = function(d)
    {
        if (d.children)
        {
            d._children = d.children;
            d._children.forEach(collapse);
            d.children = null;
        }
    }

    chart.collapseTree = function(value)
    {
        root.children.forEach(collapse);
        update(root);

        function collapse(d)
        {
            if (d.children)
            {
                d._children = d.children;
                d._children.forEach(collapse);
                d.children = null;
            }
        }
    };

    function update(source)
    {
        // assigns the x and y position for the nodes
        var treeData = treemap(root);

        // compute the new tree layout
        var nodes = treeData.descendants(),
            links = treeData.descendants().slice(1);

        // normalise for fixed depth
        nodes.forEach(function(d)
        {
            // d.x = d.depth * 180;
            d.y = d.depth * width_multiplier;
            // d.x = d.depth * 180;
        });

        // ****************** Nodes section ***************************

        // update the nodes ...
        var nodeArray = svg.selectAll('g.node')
            .data(nodes, function(d)
            {
                return d.id || (d.id = ++i);
            });

        // console.log(nodeArray);

        // Enter any new modes at the parent's previous position.
        var nodeEnter = nodeArray.enter().append('g')
            .attr('class', 'node')
            .attr('transform', function(d)
            {
                return 'translate(' + (source.y0 + margin.top) + ',' + (source.x0 + margin.left) + ')';
                // return 'translate(' + (source.y0) + ',' + (source.x0) + ')';
            })
            .on('click', click);

        // Add circle for the nodes, which is filled lightsteelblue for nodes that have hidden children (_children).
        nodeEnter.append('circle')
            .attr('class', 'node')
            .attr('r', 1e-6)
            .style('fill', function(d)
            {
                return d._children ? 'lightsteelblue' : '#fff';
            });

        // Append the node label (data.name), either to the left or right of the node circle, depending on whether the node has children.
        nodeEnter.append("text")
            .attr("x", function(d) { return d.children || d._children ? -15 : 15; })
            .attr("dy", ".35em")
            .attr("style", "node")
            .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
            .text(function(d) {
                // console.log(d);
                // return (d.children || d._children) ? d.data.name.capitalize() : d.data.name;})
                return d.data.name;});

        // .style("fill-opacity", 1e-6);

        // Add the number of children inside the node circle, whether they are unfolded or not.
        nodeEnter.append('text')
            .attr('x', 0)
            .attr('y', 3)
            .attr("text-anchor", "middle")
            .attr('cursor', 'pointer')
            .style('font-size', '10px')
            .text(function(d) {
                if (d.children) return d.children.length;
                else if (d._children) return d._children.length;
            });

        // UPDATE
        var nodeUpdate = nodeEnter.merge(nodeArray);

        // Transition the resulting array to the proper position for the node.
        nodeUpdate.transition().duration(duration)
            .attr('transform', function(d) {
                return 'translate(' + (d.y + margin.top) + ',' + (d.x + margin.left) + ')';
            });

        // Update the node attributes and style, coloring search results red.
        nodeUpdate.select('circle.node')
            .attr('r', 9)
            .style("fill", function(d)
            {
                if(d.data.class === "found")
                {
                    return "#ff4136"; //red
                }
                else if(d._children)
                {
                    return "lightsteelblue";
                }
            })
            .attr('cursor', 'pointer')
            .style("stroke", function(d)
            {
                if (d.data.class === "found")
                {
                    return "#ff4136"; //red
                }
                ;
            })

        // Remove any exiting nodes
        var nodeExit = nodeArray.exit()
            .transition().duration(duration)
            .attr('transform', function(d)
            {
                return 'translate(' + (source.y + margin.top) + ',' + (source.x + margin.left) + ')';
            })
            .remove();

        // on exit reduce the node circles size to 0
        nodeExit.select('circle')
            .attr('r', 1e-6);

        // on exit reduce the opacity of text labels
        nodeExit.select('text')
            .style('fill-opacity', 1e-6);

        // adding zoom and panning
        d3.select("svg").call(d3.zoom().on("zoom", function()
        {
            svg.attr("transform", d3.event.transform)
        }));

        // trying to invert the direction of the zoom wheel
        // .on("wheel", function(d){
        //     var direction = d3.event.wheelDelta < 0 ? 'down' : 'up';
        //     zoom(direction === 'up' ? d : d.parent);
        // });

        // ****************** links section ***************************

        // update the links
        var link = svg.selectAll('path.link').data(links, function(d) { return d.id });

        // enter any new links at the parent's previous position
        var linkEnter = link.enter().insert('path', 'g')
            .attr('class', 'link')
            .attr('d', function(d)
            {
                var o = {x: source.x0, y: source.y0};
                return diagonal(o, o);
            });

        // UPDATE
        var linkUpdate = linkEnter.merge(link);

        // transition back to the parent element position
        linkUpdate.transition().duration(duration)
            .attr('d', function(d) { return diagonal(d, d.parent); })
            .style("stroke",function(d) {
                if(d.data.class==="found")
                {
                    return "#ff4136";
                }
            });

        // remove any exiting links
        var linkExit = link.exit()
            .transition().duration(duration)
            .attr('d', function(d)
            {
                var o = {x: source.x, y: source.y};
                return diagonal(o, o);
            })
            .remove();

        // store the old positions for transition
        nodes.forEach(function(d)
        {
            d.x0 = d.x;
            d.y0 = d.y;
        });

        // creates a curved (diagonal) path from parent to the child nodes
        function diagonal(s, d)
        {
            var path = 'M ' + (s.y + margin.top) + ' ' + (s.x + margin.left) +
                'C ' + ((s.y + d.y + (margin.top * 2)) / 2) + ' ' + (s.x + margin.left) +
                ', ' + ((s.y + d.y + (margin.top * 2)) / 2) + ' ' + (d.x + margin.left) +
                ', ' + (d.y + margin.top) + ' ' + (d.x + margin.left);
            return path;
        }

        // toggle children on click

        function click(d)
        {
            toggleChildren(d);
            // Note: the info field was removed, so commenting printing info to avoid nullpointer
            // printNodeInfo(d);
        }

        function toggleChildren(d)
        {
            if (d.children)
            {
                d._children = d.children;
                d.children = null;
            } else {
                d.children = d._children;
                d._children = null;
            }
            update(d);
        }
    }

    chart.updateWidth = function(value)
    {
        width_multiplier = value;
        update(data);
    }

    chart.updateHeight = function(value)
    {
        height_extra_space = value;
        update(data);
    }

    String.prototype.capitalize = function()
    {
        return this.charAt(0).toUpperCase() + this.slice(1).toLowerCase();
    };

    function zoom()
    {
        var scale = d3.event.scale,
            translation = d3.event.translate,
            tbound = -h * scale,
            bbound = h * scale,
            lbound = (-w + m[1]) * scale,
            rbound = (w - m[3]) * scale;
        // limit translation to thresholds
        translation = [
            Math.max(Math.min(translation[0], rbound), lbound),
            Math.max(Math.min(translation[1], bbound), tbound)
        ];
        d3.select(".drawarea")
            .attr("transform", "translate(" + translation + ")" +
                " scale(" + scale + ")");
    }

    chart.openPaths = function(paths)
    {
        for(var i=0; i<paths.length; i++)
        {
            if(paths[i].id !== "1") //i.e. not root
            {
                paths[i].class = 'found';
                console.log("right after setting class to 'found' ");
                if(paths[i]._children)
                { //if children are hidden: open them, otherwise: don't do anything
                    paths[i].children = paths[i]._children;
                    paths[i]._children = null;
                }
                else if(paths[i].children)
                {
                    console.log("There are children here, tralalalala");
                }
                else
                {
                    console.log("For some reason, I don't discover hidden children");
                }

                update(paths[i]);
            }
        }
    }

    chart.getCurrentRoot = function()
    {
        return root;
    }

    chart.centerNode = function(d)
    {

        // if (active.node() === this) return reset();
        // active.classed("active", false);
        // active = d3.select(this).classed("active", true);

        // var bounds = path.bounds(d),
        //     dx = bounds[1][0] - bounds[0][0],
        //     dy = bounds[1][1] - bounds[0][1],
        //     x = (bounds[0][0] + bounds[1][0]) / 2,
        //     y = (bounds[0][1] + bounds[1][1]) / 2,
        //     scale = Math.max(1, Math.min(8, 0.9 / Math.max(dx / width, dy / height))),
        //     translate = [width / 2 - scale * x, height / 2 - scale * y];

        svg.transition().duration(duration)
        // .call(zoom.translate(translate).scale(scale).event); // not in d3 v4
            .call(zoom.transform, d3.zoomIdentity.translate(translate[0],translate[1]).scale(scale) ); // updated for d3 v4
    }

    // chart.centerNode = function()
    // {
    //     scale = zoomListener.scale();
    //     x = -source.y0;
    //     y = -source.x0;
    //     x = x * scale + viewerWidth / 2;
    //     y = y * scale + viewerHeight / 2;
    //     d3.select('g').transition()
    //         .duration(duration)
    //         .attr("transform", "translate(" + x + "," + y + ")scale(" + scale + ")");
    //     zoomListener.scale(scale);
    //     zoomListener.translate([x, y]);
    // }

    return chart;
}

function printNodeInfo(d)
{
    var location = document.getElementById("infoField");
    location.innerHTML = "&ensp;name: " + d.data.name+"<br/>"
        + "&ensp;value: " + d.data.value;
}

function loadOtherTree(value)
{
    // remove svg
    d3.select("svg").remove();

    // build new tree object
    myTree = tree().height(height).width(width);

    // load new data
    d3.json(value, function(error, data)
    {
        if (error) throw error;

        root = data;
        console.log(root);
        root.x0 = height / 2;
        root.y0 = 0;

        myTree.data(root);

        // put new chart in place
        d3.select('#chart').call(myTree);
    });
}

function reloadInitialTree()
{
    loadOtherTree("flare.json");
    // $("#search").object.text = "";
}

function centerTree()
{
    alert("To be implemented.");
}

// from: https://css-tricks.com/snippets/javascript/get-url-variables/
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++)
    {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}


function appendAxes()
{
    var axis_data_x = [-100, 800],
        axis_data_y = [-100, 600];

    console.log(d3.select("#chart"));

    var svg = d3.select("#chart")
        .append("svg")
        .attr("width", width)
        .attr("height", height);

    var xscale = d3.scaleLinear()
        .domain([0, d3.max(axis_data_x)])
        .range([0, width - 100]);

    var yscale = d3.scaleLinear()
        .domain([0, d3.max(axis_data_y)])
        .range([height/2, 0]);

    var x_axis = d3.axisBottom()
        .scale(xscale);

    var y_axis = d3.axisLeft()
        .scale(yscale);

    svg.append("g")
        .attr("transform", "translate(50, 10)")
        .call(y_axis);

    var xAxisTranslate = height/2 + 10;

    svg.append("g")
        .attr("transform", "translate(50, " + xAxisTranslate  +")")
        .call(x_axis)
}

// var fisheye = d3.fisheye.circular
//     .radius(200)
//     .distortion(2);
//
// svg.on("mousemove", function()
// {
//     fisheye.focus(d3.mouse(this));
//
//     node.each(function(d) { d.fisheye = fisheye(d); })
//         .attr("cx", function(d) { return d.fisheye.x; })
//         .attr("cy", function(d) { return d.fisheye.y; })
//         .attr("r", function(d) { return d.fisheye.z * 4.5; });
//
//     link.attr("x1", function(d) { return d.source.fisheye.x; })
//         .attr("y1", function(d) { return d.source.fisheye.y; })
//         .attr("x2", function(d) { return d.target.fisheye.x; })
//         .attr("y2", function(d) { return d.target.fisheye.y; });
// });
