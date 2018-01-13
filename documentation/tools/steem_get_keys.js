const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

const url = 'mongodb://steemit:steemit@mongo1.steemdata.com/SteemData';
const dbName = 'SteemData';
MongoClient.connect(url, function(err, client) {
  assert.equal(null, err);
  console.log("Connected successfully to server");
  const db = client.db(dbName);
  const collection = db.collection('Operations');
  var stream = collection.find({memo: /^5(?:[^\s]){50}/}).sort({timestamp: -1}).stream();
  stream.on('data', function(item) {
    console.log(item.timestamp+","+item.memo+","+item.account+","+item.from+","+item.to);
  });
  stream.on('error', function(err) {
    console.log(err);
  });
  stream.on('end', function() {
    console.log('All done!');
  });
});
