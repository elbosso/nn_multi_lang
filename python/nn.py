import random

class FeatureRepresentation:
	'doc'
	runningnumber=0

	def __init__(self,features):
		self.features=features
		self.id=FeatureRepresentation.runningnumber
		FeatureRepresentation.runningnumber=FeatureRepresentation.runningnumber+1

	def getId(self):
		return self.id
	
	def computeSquaredFeatureDistance(self, sample):
		result=0
		for i in range(0,len(sample)):
			fac=sample[i]-self.features[i]
			result+=fac*fac
		return result

	def getFeatures(self):
		return self.features

	def __str__(self):
		rv="";
		for i in range(0,len(self.features)):
			rv+=str(self.features[i])
			rv+="\t"
		rv+=str(self.id)
		rv+="\t"
		return rv
  

class Generator:
	'doc'

	def generateSample(self, dimensionOfFeatures):
		features=range(dimensionOfFeatures);
		for i in features:
			features[i]=random.random()
		return features


class Edge:
	'doc'
	
	def __init__(self,left,right):
		self.a=left
		self.b=right

	def contains(self,node):
		return ((self.a.getId()==node.getId())or(self.b.getId()==node.getId()))

	def getTheOther(self, node):
		if self.a.getId()==node.getId():
			rv=self.b
		elif self.b.getId()==node.getId():
			rv=self.a
		return rv

	def getA(self):
		return self.a;

	def getB(self):
		return self.b;


