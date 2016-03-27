using System;

namespace nn
{
	public class GnuplotRenderer<T,U> where T : FeatureRepresentation where U : Edge<T>
	{
		public static void outNodes(string basename, int steps, System.Collections.Generic.ICollection<T> features) 
		{
			// encodes the output as text.
			using (System.IO.StreamWriter file = 
			       new System.IO.StreamWriter(System.IO.Path.GetTempPath()+basename+" "+steps+".dat"))
			{
				foreach (FeatureRepresentation feature in features)
				{
					file.WriteLine(feature);
				}
			}
		}
		public static void outEdges(string basename, int steps, System.Collections.Generic.ICollection<U> edges) 
		{
			// encodes the output as text.
			using (System.IO.StreamWriter file = 
			       new System.IO.StreamWriter(System.IO.Path.GetTempPath()+basename+" "+steps+".dat"))
			{
				foreach (Edge<T> edge in edges)
				{
					file.Write(edge.A);
					file.Write(edge.B);
					file.WriteLine();
				}
			}
		}
	}
}

