#import os,sys,inspect
import math

class Node(nn.FeatureRepresentation):
	'doc'

	def __init__(self, features):
		nn.FeatureRepresentation.__init__(self,features);

	def computeSquaredFeatureDistance(self, sample):
		squaredFeaturesDistance=super(Node, self).computeSquaredFeatureDistance(sample)
		return squaredFeaturesDistance;

	def getSquaredFeaturesDistance(self):
		return self.squaredFeaturesDistance;

	def updateFeatures(self, e,  lamb, sample, k):
		h=math.exp(-k/lamb)
		for i in range(0,len(sample)):
			features[i]+=e*h*(sample[i]-features[i])

gen=nn.Generator()
features=gen.generateSample(2)
sample=gen.generateSample(2)
a=Node(features);
print a
a.updateFeatures(0.6,5,sample,3)
print a
