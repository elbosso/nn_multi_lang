import random

class Generator:
	'doc'

	def generateSample(self, dimensionOfFeatures):
		features=range(dimensionOfFeatures);
		for i in features:
			features[i]=random.random()
		return features

