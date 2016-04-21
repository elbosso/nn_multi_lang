function Generator() {
  this.generateNewSample=function(dimensionOfFeatures)
  	{
  		var features=[];
      var i=0;
      for(i=0;i<dimensionOfFeatures;++i)
        features[i]=0.0;
  		this.generateStandardSample(features);
  		return features;
  	}
  	this.generateStandardSample=function(features)
  	{
      var i=0;
  		for (i=0;i<features.length;++i)
  		{
  			features[i]=Math.random();
  		}
  	}
    this.generate=function(nodesperdimension, dimensionOfFeatures)
	{
		var total=1;
    var i=0;
		for (i=0;i< nodesperdimension.length;++i)
		{
			total*=nodesperdimension[i];
		}
		var nodes=[];
		var counters=[];
		for(i=0;i<nodesperdimension.length;++i)
		{
			counters[i]=0.0;
		}
		var loop=0;
		while(true)
		{
			var locations=[];
      for(i=0;i<nodesperdimension.length;++i)
		  {
			   locations[i]=counters[i];
		  }
			var features=[];
			for (i=0;i<dimensionOfFeatures;++i)
			{
				features[i]=Math.random();
			}
			nodes[loop]=new Node(features,locations);
			++loop;
			counters[0]++;
			for(i=0;i<counters.length;++i)
			{
				if(counters[i]==nodesperdimension[i])
				{
					if(i+1<nodesperdimension.length)
					{
						counters[i+1]++;
						counters[i]=0;
					}
				}
				else
					break;
			}
			if(loop==total)
				break;
		}
		return nodes;
	}
	this.generateSample=function(features)
	{
		this.generateStandardSample(features);
		if(features[1]>features[0])
		{
			features[1]*=features[0];//=1-features[1];
		}
	}
}
