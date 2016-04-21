Renderer=function(){
this.outEdges=function(document,root,edges)
	{
/*    var svgElement = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svgElement.width = 400;
    svgElement.height = 400;
    document.getElementsByTagName("div")[0].appendChild(svgElement);

    var rectElement = document.createElementNS("http://www.w3.org/2000/svg", "rect");
    rectElement.setAttribute("fill", "#ff0000");
    rectElement.setAttribute("width", "200px");
    rectElement.setAttribute("height", "200px");

    svgElement.appendChild(rectElement);
*/
		var element = document.createElementNS("http://www.w3.org/2000/svg", "svg");
		root.appendChild(element);
/*		element.setAttribute("xmlns:svg","http://www.w3.org/2000/svg");
		element.setAttribute("xmlns","http://www.w3.org/2000/svg");
*/		element.setAttribute("width", "100.0");
		element.setAttribute("height", "100.0");
		var group = document.createElementNS("http://www.w3.org/2000/svg", "g");
		group.setAttribute("transform", "translate(0,100) scale(1,-1)");
		element.appendChild(group);
    var i=0;
		for (i=0;i<edges.length;++i)
		{
			var path = document.createElementNS("http://www.w3.org/2000/svg", "path");
			group.appendChild(path);
			var sb='M ';
			sb+=edges[i].a.features[0]*100.0;
			sb+=' ';
			sb+=edges[i].a.features[1]*100.0;
			sb+=' L';
			sb+=edges[i].b.features[0]*100.0;
			sb+=' ';
			sb+=edges[i].b.features[1]*100.0;
			path.setAttribute("d", sb);
			path.setAttribute("style","fill:none;fill-opacity:1;stroke:#000000;stroke-opacity:1;stroke-width:1;stroke-miterlimit:4;stroke-dasharray:none");
		}
}
}
