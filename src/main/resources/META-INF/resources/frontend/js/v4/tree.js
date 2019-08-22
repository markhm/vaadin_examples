var treeAlreadyRendered = false;

var height = 700 - 4; // 2px border

var width = 960 - 4; // 2px border

var myTree = tree().height(height).width(width);

function renderTree()
{
    var div = d3.select("body")
        .append("div") // declare the tooltip div
        .attr("class", "tooltip")
        .style("opacity", 0);

    // d3.json("https://raw.githubusercontent.com/d3/d3-hierarchy/master/test/data/flare.json", function(error, values){
    d3.json("http://localhost:8081/frontend/js/flare_partly.json", function (error, values) {
    // d3.json("http://localhost:8080/tree_data/", function (error, values) {
        if (error) throw error;

        root = values;

        myTree.data(root);

        d3.select('#tree').call(myTree);

    });
}

// window.onload=renderTree;