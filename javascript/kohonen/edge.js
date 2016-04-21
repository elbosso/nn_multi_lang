Edge=function(a,b){
  this.a=a;
  this.b=b;

  this.getTheOther=function(node)
	{
	   var rv=null;
/*		if(a.getId()==node.getId())
			rv=b;
		else if(b.getId()==node.getId())
			rv=a;
*/		return rv;
	}
}
