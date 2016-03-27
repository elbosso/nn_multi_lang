using System;

namespace nn
{
	public class Edge <T> where T : FeatureRepresentation
	{
		private T a;
		private T b;

		public T A
		{
			get
			{
				return a;
			}
			set
			{
				a=value;
			}
		}
		public T B
		{
			get
			{
				return b;
			}
			set
			{
				b=value;
			}
		}

			public Edge(T a, T b):base()
			{
				this.a = a;
				this.b = b;
			}
			public bool contains(T node)
			{
				return ((a.Id==node.Id)||(b.Id==node.Id));
			}
			public T getTheOther(T node)
			{
				T rv=null;
				if(a.Id==node.Id)
					rv=b;
				else if(b.Id==node.Id)
					rv=a;
				return rv;
			}
	}
}

