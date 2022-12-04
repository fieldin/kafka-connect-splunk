curl -X POST \
  http://<kafkaconnect>>:8083/connectors \
  -H 'Content-Type: application/json' \
  -d '{ "name": "kafka-connect-splunk",
    "config":
    {
        "connector.class": "com.splunk.kafka.connect.SplunkSinkConnector",
        "tasks.max": "1",
        "topics": "emqx_test",
        "name": "kafka-connect-splunk",
        "indexes": "flory",
        "splunk.hec.token": "82f71a62-e053-49c3-8483-21155b463142",
        "splunk.hec.uri": "https://http-inputs-fieldin.splunkcloud.com:443",
        "splunk.hec.ssl.validate.certs": "false",
        "splunk.hec.raw": "true",
        "key.converter": "org.apache.kafka.connect.storage.StringConverter",
        "key.converter.schemas.enable": "false",
        "value.converter": "com.splunk.kafka.connect.MsgPackConverter",
        "value.converter.schemas.enable": "false",
        "splunk.hec.ack.enabled": "true"
    }
}'