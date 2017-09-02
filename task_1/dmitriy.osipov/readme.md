You can run Main for demonstration.
I created 3 interfaces: IElement, IElementLead and IElementContent.

Also I created 4 models:
 - GitStructure - can contain only Commits;
 - Commit - implements IElementLead, can contain Trees and Blobs;
 - Tree - implements IElementContent and IElementLead, can contain other Trees and Blobs;
 - Blob - implements IElementContent and can contain only some String value.

 Each of models, except GitStructure, implements IElement.

 GitStructure has methods for:
 - writing all commits to the console;
 - writing all commits with their content to the console;
 - save commits to different files named by their sha-1 codes (I used serialization here, look to
 "results" folder after running Main);
 - save all elements to different files named by their sha-1 codes (here I convert every object to
 array of bytes, encode them with sha-1 and write into the files. look to the "resultsDF" folder
 after running Main)