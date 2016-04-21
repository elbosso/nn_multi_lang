function Node(feat,loc) {

    this.locations = loc.slice();
    this.distanceFromWinner =0.0;
    this.features = feat.slice();


    this.computeSquaredFeatureDistance=function(sample){
    		var result=0.0;
        var i=0;
    		for (i=0;i<sample.length;++i)
    		{
    			var fac=sample[i]-this.features[i];
    			result+=fac*fac;
    		}
    		return result;
    	}

      this.updateDistanceSquaredFromWinner=function(winner)
	{
		var result=0.0;
    var i=0;
		for (i=0;i<this.locations.length;++i)
		{
			var fac=1.0*winner.locations[i]-this.locations[i];
			result+=fac*fac;
		}
		this.distanceFromWinner=result;
	}
  this.updateFeatures=function(e, deltasquared,sample)
  	{
  		var rv=this.distanceFromWinner<deltasquared;
  		if(rv==true)
  		{
  				var h=Math.exp(-this.distanceFromWinner/(2.0*deltasquared));
          var i=0;
  			for (i=0;i<sample.length;++i)
  			{
  				this.features[i]+=e*h*(sample[i]-this.features[i]);
  			}
  		}
  		return rv;
  	}
    this.toString=function(){
      var rv='';
      var i=0;
      for(i=0;i<this.locations.length;++i)
      {
        rv+=this.locations[i];
        rv+='\t';
      }
      for(i=0;i<this.features.length;++i)
      {
        rv+=this.features[i];
        rv+='\t';
      }
      return rv;
    }
}
