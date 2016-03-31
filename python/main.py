import neuralgas
import util

gen=neuralgas.Generator()
svggen=util.SvgRenderer()
nodes=gen.generate(25,2)
network=neuralgas.Network(nodes)
network.init(10000,1.0,0.001,13,0.01)
i=0
plotinterval=2000
svggen.outNodes("neuralgas",`0`,network.getNodes());
svggen.outNodes("neuralgas",`1`,network.getNodes());
for i in range(0, 10000):
    sample=gen.generateSample(2);
    network.learn(sample);
    if (i+1)%plotinterval==0:
        svggen.outNodes("neuralgas",`i+1`,network.getNodes());
