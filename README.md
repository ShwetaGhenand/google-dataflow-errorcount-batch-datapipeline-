# dataflow-errorcount

The ErrorCount examples demonstrate how to set up a batch processing pipeline that can read log from GCS(Google Cloud Storage), perform a frequency count on each of errors and write output file to GCS.

![image](https://user-images.githubusercontent.com/42492058/59149469-08f8d080-8a33-11e9-81d6-5d3c3f88cb1e.png)

#### Environment Setup
1. Create a account on Google Cloud Platform(GCP).
2. Install the Cloud Tools for Eclipse plugin and provide authentication credentials to your application code by setting the environment variable GOOGLE_APPLICATION_CREDENTIALS.
3. Installl Cloud Storage Client Libraries(Add dependancy to pom.xml file)	
 If you are using Maven, add the following to your pom.xml file:
```
<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-storage</artifactId>
  <version>1.77.0</version>
</dependency>
```
#### Implementation

![image](https://user-images.githubusercontent.com/42492058/59149676-3d21c080-8a36-11e9-869f-2a885342f24c.png)

1. Create the Pipeline.
2. Apply transforms to read the bounded log data  from a GCS into the Pipeline.
3. Apply ExtractError transform to find  error from log file.
4. Applying SDK-provided transforms (Count.globally) which calculate the number of elements of PCollection.
5. Writing output file to GCS.
7. Run the pipeline both locally and using  cloud Dataflow service.

#### Data Pipeine Execution:
Run ErrorCount locally
```
1. mvn clean
2. mvn install
3. mvn compile exec:java -Dexec.mainClass=com.example.StarterPipeline  -Dexec.args="--myCustomOption=gs://gcp-bucket-test-01/input-data/sample.log"
```
Run ErrorCount on the Cloud Dataflow service
```
1.mvn clean
2.mvn install
3.mvn compile exec:java -Dexec.mainClass=com.example.StarterPipeline -Dexec.args=" --runner=DataflowRunner --myCustomOption=gs://gcp-bucket-test-01/input-data/sample.log"  -Pdataflow-runner
```
Note: Set the  myCustomOption option to choose a different input file .

