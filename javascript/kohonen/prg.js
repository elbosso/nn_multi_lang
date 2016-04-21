main=function(){
  var renderer=new Renderer();
  var generator=new Generator();
  var nodesperdimension=[5,5];
  var dimensionOfFeatures=2;
  var nodes=generator.generate(nodesperdimension, dimensionOfFeatures);
  println(nodes[0]);
  println(nodes.length);
  var network=new Network(nodes);
  var sample=generator.generateNewSample(dimensionOfFeatures);
  network.init(10000,0.8,0.03,5.1,1.2);
  var i=0;
  var plotinterval=2;
  for(i=0;i<10000;++i)
  {
    generator.generateSample(sample);
    network.learn(sample);
    if((i+1)%plotinterval==0)
    {
      var root = document.getElementById("canvas");
      root.innerHTML='';
      var edges=network.getEdges();
      renderer.outEdges(document,root,edges);
    }
  }
  println(nodes[0]);
}
