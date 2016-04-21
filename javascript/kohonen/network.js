function Network(nds){

  this.nodes=nds.slice();
  this.tmax=0.0;
  this.estart=0.0;
  this.eend=0.0;
  this.deltastart=0.0;
  this.deltaend=0.0;
  this.t=0.0;

  this.init=function(tmax, estart, eend, deltastart, deltaend)
  	{
  		this.t=0;
  		this.tmax=tmax;
  		this.estart=estart;
  		this.eend=eend;
  		this.deltastart=deltastart;
  		this.deltaend=deltaend;
  	}
    this.learn=function( sample)
    	{
    		var featureDistMin=Number.MAX_VALUE;
    		var winner;
        var i=0;
    		for (i=0;i<this.nodes.length;++i)
    		{
    			var dist=this.nodes[i].computeSquaredFeatureDistance(sample);
    			if(dist<featureDistMin)
    			{
    				winner=this.nodes[i];
    				featureDistMin=dist;
    			}
    		}
  		for (i=0;i<this.nodes.length;++i)
    		{
    			this.nodes[i].updateDistanceSquaredFromWinner(winner);
    		}
    		this.nodes.sort(function(a, b){return a.distanceFromWinner-b.distanceFromWinner});
    		var timeq=this.t/this.tmax;
    		var e=this.estart*(Math.pow(this.eend/this.estart, timeq));
    		var delta=this.deltastart*(Math.pow(this.deltaend/this.deltastart, timeq));
    		delta*=delta;
    		for (i=0;i<this.nodes.length;++i)
    		{
    			if(this.nodes[i].updateFeatures(e, delta,sample)==false)
    				break;
    		}

    		this.t+=1;
    	}
      this.getEdges=function()
	{
    var edges=[];
    var j=0;
    var i=0;
    var loop=0;
			for(j=0;j<this.nodes.length;++j)
			{
				var locations=this.nodes[j].locations;
				for(i=0;i<locations.length;++i)
				{
					if(locations[i]>0)
					{
						var theOther=this.searchNeighbor(this.nodes[j],i);
						if(!(theOther === null && typeof theOther === "object"))
            {
							edges[loop]=new Edge(this.nodes[j],theOther);
              ++loop;
            }
					}
				}
			}

		return edges;
	}
	this.searchNeighbor=function(me,index)
	{
		var rv=null;
    var j=0;
    var i=0;
			for(j=0;j<this.nodes.length;++j)
			{
			var locations=this.nodes[j].locations;
			rv=this.nodes[j];
			for(i=0;i<locations.length;++i)
			{
				if(i!=index)
				{
					if(me.locations[i]!=locations[i])
					{
						rv=null;
						break;
					}
				}
				else
				{
					if(me.locations[i]-1!=locations[i])
					{
						rv=null;
						break;
					}
				}
			}
			if(rv!=null)
				break;
		}
		return rv;
	}
}
