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


