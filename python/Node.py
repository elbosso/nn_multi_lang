#http://stackoverflow.com/questions/714063/python-importing-modules-from-parent-folder
import os,sys,inspect
currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
parentdir = os.path.dirname(currentdir)
parentdir = os.path.dirname(parentdir)
sys.path.insert(0,parentdir) 

import FeatureRepresentation
import Generator
import math

class Node(FeatureRepresentation.FeatureRepresentation):
	'doc'

	def __init__(self, features):
		FeatureRepresentation.FeatureRepresentation.__init__(self,features);

	def computeSquaredFeatureDistance(self, sample):
		squaredFeaturesDistance=super(Node, self).computeSquaredFeatureDistance(sample)
		return squaredFeaturesDistance;

	def getSquaredFeaturesDistance(self):
		return self.squaredFeaturesDistance;

	def updateFeatures(self, e,  lamb, sample, k):
		h=math.exp(-k/lamb)
		for i in range(0,len(sample)):
			features[i]+=e*h*(sample[i]-features[i])

gen=Generator.Generator()
features=gen.generateSample(2)
sample=gen.generateSample(2)
a=Node(features);
print a
a.updateFeatures(0.6,5,sample,3)
print a
