def annotations = getAnnotationObjects()

def detections = annotations.collect { ann ->
    def det = PathObjects.createDetectionObject(
        ann.getROI(),
        ann.getPathClass()
    )
    
    // Copy measurements safely
    det.getMeasurementList().putAll(ann.getMeasurementList())
    det
}

addObjects(detections)

// Optional: remove original annotations
removeObjects(annotations, true)
