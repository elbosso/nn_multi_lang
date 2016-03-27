class FeatureRepresentation:
	'doc'
	runningnumber=0

	def __init__(self,features):
		self.features=features
		self.id=FeatureRepresentation.runningnumber
		++FeatureRepresentation.runningnumber

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


