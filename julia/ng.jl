# https://learnxinyminutes.com/docs/julia/
# https://en.wikibooks.org/wiki/Introducing_Julia/Arrays_and_tuples
using PyPlot


type Node
	squaredFeaturesDistance
	features
end

function updateFeatures(node::Node, e, lambda, sample, k)
	h=exp(-k/lambda)
	i=1
	while i<=length(sample)
		node.features[i]+=e*h*(sample[i]-node.features[i])
		i+=1
	end
end

function computeSquaredFeatureDistance(node::Node, sample)
	result=0
	i=1
	while i<=length(sample)
		fac=sample[i]-node.features[i]
		result+=fac*fac
		i+=1
	end
	node.squaredFeaturesDistance=result
	return node.squaredFeaturesDistance
end

type Network
	nodes
	tmax
	estart
	eend
	lambdastart
	lambdaend
	t
end

function init(network::Network,tmax,estart,eend,lambdastart,lambdaend)
	network.t=0
	network.tmax=tmax
	network.estart=estart
	network.eend=eend
	network.lambdastart=lambdastart
	network.lambdaend=lambdaend
end

function nodeSort(node::Node)
	return node.squaredFeaturesDistance
end

function learn(network::Network,sample)
	featureDistMin=typemax(Float64)
	winner=network.nodes[1]
	
	loop=1;
	while loop<=length(network.nodes)
		dist=computeSquaredFeatureDistance(network.nodes[loop],sample)
		if(dist<featureDistMin)
			winner=network.nodes[loop]
			featureDistMin=dist
		end
		loop+=1
	end
	sort!(network.nodes,by=nodeSort)
	timeq=network.t/network.tmax
	e=network.estart*((network.eend/network.estart)^ timeq)
	lambda=network.lambdastart*((network.lambdaend/network.lambdastart)^ timeq)
	i=0
	loop=1
	while loop<=length(network.nodes)
		updateFeatures(network.nodes[loop],e, lambda,sample,i)
		i+=1
		loop+=1
	end
	network.t+=1
end

function generateSample(dimensionOfFeatures)
	return rand(dimensionOfFeatures)
end

function generateNodes(numberOfNodes,dimensionOfFeatures)
	nodes=Array{Node}(numberOfNodes)
	loop=1;
	while loop<=length(nodes)
		nodes[loop]=Node(0,rand(dimensionOfFeatures))
		loop+=1
	end
	return nodes
end

nodes=generateNodes(25,2);
network=Network(nodes,10000,1.0,0.001,13,0.01,0)
init(network,10000,1.0,0.001,13,0.01)
i=0
plotinterval=1000
while i<10000
	sample=generateSample(2);
	learn(network,sample);
	i+=1;
	if (i % plotinterval)==0
		#################
		#  Create Data  #
		#################
		x = []
		y = []
		loop=1
		while loop<=length(network.nodes)
			push!(x,network.nodes[loop].features[1])
			push!(y,network.nodes[loop].features[2])
			loop+=1
		end
		##################
		#  Scatter Plot  #
		##################
		fig = figure("pyplot_scatterplot",figsize=(10,10))
		ax = axes()
		sc=scatter(x,y,s=15,alpha=0.5)

		title("Scatter Plot")
		xlabel("X")
		ylabel("Y")
		grid("on")
		PyPlot.plt[:show]()
	end
end



