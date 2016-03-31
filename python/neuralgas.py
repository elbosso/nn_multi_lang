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
		self.squaredFeaturesDistance=nn.FeatureRepresentation.computeSquaredFeatureDistance(self,sample)
		return self.squaredFeaturesDistance;

    def getSquaredFeaturesDistance(self):
		return self.squaredFeaturesDistance;

    def updateFeatures(self, e,  lamb, sample, k):
         h=math.exp(-k/lamb)
#         print `e*h`
         for i in range(0,len(sample)):
#             print i
             self.features[i]+=e*h*(sample[i]-self.features[i])
    def __str__(self):
          return `self.id`
    def __repr__(self):
          return self.__str__()
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
        self.t=0.0;
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
#        print self.nodes[0].getSquaredFeaturesDistance()
        self.nodes.sort(key=lambda x: x.squaredFeaturesDistance, reverse=False)
#        print self.nodes        
        timeq=self.t/self.tmax;
        e=self.estart*(pow(self.eend/self.estart, timeq));
        lambd=self.lambdastart*(pow(self.lambdaend/self.lambdastart, timeq));
        i=0;
        for node in self.nodes:
            node.updateFeatures(e, lambd,sample,i);
#            print i
            i=i+1
#       print '----'+`len(self.nodes)`
        self.t+=1;
#        print '--'+`e`+' '+`(pow(self.eend/self.estart, timeq))`+' '+`self.eend/self.estart`+' '+`timeq`+' '+`self.t`+' '+`self.tmax`

    def getNodes(self):
        return self.nodes