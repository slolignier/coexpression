// select detections
selectDetections();

// add intensity measurements (modify channels and features if needed)
runPlugin('qupath.lib.algorithms.IntensityFeaturesPlugin', 
    '{"pixelSizeMicrons":2.0, "region":"ROI", "tileSizeMicrons":25.0, ' +
    '"channel1":false, "channel2":true, "channel3":true, "channel4":true, ' +
    '"doMean":true, "doStdDev":true, "doMinMax":true, "doMedian":true, ' +
    '"doHaralick":true, "haralickMin":NaN, "haralickMax":NaN, ' +
    '"haralickDistance":1, "haralickBins":32}'
)
    
// add shape measurements
addShapeMeasurements("AREA", "CIRCULARITY", "MAX_DIAMETER")

// run classifier (modify classifier name if needed)
runObjectClassifier("NeuN_IB4_TREK1")

// set export path (modify if necessary)
def project = getProject()
def outputDir = buildFilePath(PROJECT_BASE_DIR, "exports")

// get image name and set export file name
def imageName = GeneralTools.stripExtension(getCurrentImageName())
def outputFile = buildFilePath(outputDir, imageName + "_classification.csv")

// save detection measurements
saveDetectionMeasurements(outputFile)
