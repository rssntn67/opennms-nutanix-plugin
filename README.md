# OpenNMS Nutanix Plugin


OpenNMS Nutanix plugin was generated by the OPA archetype.

The generated plugin contains code:
* Nutanix Api v3 Client 
* Nutanix Api Client
* Nutanix Confogurazione
* Nutanix Provisioning Extension
* Karaf Shell Command 
* REST Endpoint 
* Nutanix Event Configuration Extension 
* Alarm Lifecycle Listener 
* Nutanix Monitor 
* Nutanix Data Collection 

Nutanix v3 Api supports two fundamental end point:
vms/list -> Virtual Machines List
hosts/list -> Physical Cluster Hosts

Every host with prism installed is an api endpoint.
So we can use this information to rotate api request among cluster hosts
How to set up configuration for Nutanix Cluster (To be stored under opennms Credentials written by Karaf Shell Command):
a) define a specific cluster nutanix name
b) define a specific cluster end point for specified name: https://localhost:9440/api/nutanix/v3
c) define credentials (provide username/password or api-key)
d) define if avoiding SSL certificate verification or just write a procedure to import ssl certificate into java
e) define a strategy for accesing prism (randomly or sequencially for load bbalancing)




Build and install the plugin into your local Maven repository using:

```
mvn clean install
```
To speed up development iterations, one can use `-Dcodegen.skip=true` to skip the regeneration of the client model classes.


From the OpenNMS Karaf shell:
```
feature:repo-add mvn:org.opennms.plugins.nutanix/karaf-features/0.1.0-SNAPSHOT/xml
feature:install opennms-plugins-nutanix
```


```
cp assembly/kar/target/opennms-nutanix-plugin.kar /opt/opennms/deploy/
feature:install opennms-plugins-nutanix
```

```
bundle:watch *
```

You can also access the REST endpoint mounted by the plugin at `http://localhost:8980/opennms/rest/nutanix/`

```
Nutanix Api Support Reference 

v1 (Prism Element)
Legacy application support and VM performance metrics
Should not be used outside of specific circumstances
v2.0 (Prism Element)
Merge of v0.8 and v1 APIs into single GA API
cluster-specific tasks. For example, storage container management, storage container performance statistics
v3 (Prism Central)
environment-wide configuration and entity management
Prism Central application and product management including Nutanix Self Service, Nutanix Kubernetes Engine, Nutanix Flow
v4 (Prism Central)
Multi-cluster entity management. This includes cluster images, virtual machines, networking, AIops
See Nutanix API v4 Intro for detailed information, including official v4 API documentation