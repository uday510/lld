Imagine a system where we want to load a heavy object like a large image
from disk, it might time to load the image, and we don't want to load it 
until it's necessary. Without using a proxy, the application would load the
image every time it's needed, even if not displayed, wasting resources.