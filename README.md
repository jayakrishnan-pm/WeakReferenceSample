# WeakReferenceSample
Sample project showing how to do communication between AsyncTask and activity through a weak reference to avoid memory leak. 
This sample project will demonstrate how to make reference to Activity-like Component that is under frequent state changes. 
The solution is to create WeakReference to Activity, so the GC can free up the memory whenever it finds the Activity scope is reached to its end.
