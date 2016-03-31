#import os,sys,inspect
import math
import nn
import random
import sys

class Node(nn.FeatureRepresentation):
	'doc'

	def __init__(self, features):
		nn.FeatureRepresentation.__init__(self,features);

	def computeSquaredFeatureDistance(self, sample):
		squaredFeaturesDistance=nn.FeatureRepresentation.computeSquaredFeatureDistance(self,sample)
		return squaredFeaturesDistance;

	def getSquaredFeaturesDistance(self):
		return self.squaredFeaturesDistance;

	def updateFeatures(self, e,  lamb, sample, k):
		h=math.exp(-k/lamb)
		for i in range(0,len(sample)):
			self.features[i]+=e*h*(sample[i]-self.features[i])

class Generator(nn.Generator):
    'doc'
        
    def generate(self,nodeCount,dimensionOfFeatures):
        nodes=range(nodeCount);
        for j in nodes:
            features=range(dimensionOfFeatures)
            for i in features:
                features[i]=random.random()
            nodes[j]=Node(features)
        return nodes;

class Network:
    'doc'
    
    def __init__(self,nodes):
        self.nodes=nodes;
        
    def init(self,tmax,estart,eend,lambdastart,lambdaend):
        self.t=0;
        self.tmax=tmax
        self.estart=estart
        self.eend=eend;
        self.lambdastart=lambdastart;
        self.lambdaend=lambdaend;

    def getT(self):
        return self.t;
        
    def learn(self, sample):
        featureDistMin=sys.float_info.max
        for node in self.nodes:
            dist=node.computeSquaredFeatureDistance(sample)
            if dist<featureDistMin:
                winner=node
                featureDistMin=dist
        timeq=self.t/self.tmax;
        e=self.estart*(pow(self.eend/self.estart, timeq));
        lambd=self.lambdastart*(pow(self.lambdaend/self.lambdastart, timeq));
        i=0;
        for node in self.nodes:
            node.updateFeatures(e, lambd,sample,i);
            ++i
        self.t+=1;

gen=Generator()
nodes=gen.generate(25,2)
network=Network(nodes)
network.init(10000,1.0,0.001,13,0.01)
i=0
plotinterval=12000
for i in range(0, 10000):
    sample=gen.generateSample(2);
    network.learn(sample);
#    if (i+1)%plotinterval==0:
#        SVGRenderer.outNodes("neuralgas",i+1,java.util.Arrays.asList(network.getNodes()));
