using System;
using System.Xml;

namespace nn
{
	public class SvgRenderer<T,U> where T : FeatureRepresentation where U : Edge<T>
	{
		public static void outNodes(string basename, int steps, System.Collections.Generic.ICollection<T> features) 
		{
			XmlDocument doc=new XmlDocument();
			XmlElement root=doc.CreateElement("svg");
			doc.AppendChild(root);
			root.SetAttribute("xmlns:svg","http://www.w3.org/2000/svg");
			root.SetAttribute("xmlns","http://www.w3.org/2000/svg");
			root.SetAttribute("width", "1000.0");
			root.SetAttribute("height", "1000.0");
			XmlElement group=doc.CreateElement("g");
			group.SetAttribute("transform","translate(0,1000) scale(1,-1)");
			root.AppendChild(group);
			foreach (FeatureRepresentation feature in features)
			{
				XmlElement path = doc.CreateElement("circle");
				group.AppendChild(path);
				path.SetAttribute("cx", ""+(feature.Features[0]*1000));
				path.SetAttribute("cy", ""+(feature.Features[1]*1000));
				path.SetAttribute("r", "23");
				path.SetAttribute("style","color:#000000;clip-rule:nonzero;display:inline;overflow:visible;visibility:visible;opacity:0.56999984;color-interpolation:sRGB;color-interpolation-filters:linearRGB;fill:#00ff00;fill-opacity:1;fill-rule:nonzero;stroke:#000000;stroke-width:0.01;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;marker:none;enable-background:accumulate");
			}
			doc.Save(System.IO.Path.GetTempPath()+basename+" "+steps+".svg");
		}
		public static void outEdges(string basename, int steps, System.Collections.Generic.ICollection<U> edges) 
		{
			XmlDocument doc=new XmlDocument();
			XmlElement root=doc.CreateElement("svg");
			doc.AppendChild(root);
			root.SetAttribute("xmlns:svg","http://www.w3.org/2000/svg");
			root.SetAttribute("xmlns","http://www.w3.org/2000/svg");
			root.SetAttribute("width", "1000.0");
			root.SetAttribute("height", "1000.0");
			XmlElement group=doc.CreateElement("g");
			group.SetAttribute("transform","translate(0,1000) scale(1,-1)");
			root.AppendChild(group);
			foreach (Edge<T> edge in edges)
			{
				XmlElement path = doc.CreateElement("path");
				group.AppendChild(path);
				System.Text.StringBuilder sb=new System.Text.StringBuilder("M");
				sb.Append(edge.A.Features[0]*1000);
				sb.Append(" ");
				sb.Append(edge.A.Features[1]*1000);
				sb.Append(" L");
				sb.Append(edge.B.Features[0]*1000);
				sb.Append(" ");
				sb.Append(edge.B.Features[1]*1000);
				path.SetAttribute("d", sb.ToString());
				path.SetAttribute("style","fill:none;fill-opacity:1;stroke:#000000;stroke-opacity:1;stroke-width:1;stroke-miterlimit:4;stroke-dasharray:none");
			}
			doc.Save(System.IO.Path.GetTempPath()+basename+" "+steps+".svg");
		}
	}
}

