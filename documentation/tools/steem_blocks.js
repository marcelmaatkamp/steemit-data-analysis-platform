const Promise = require('bluebird');
var regexp = require('node-regexp')

// steemit

var steem = Promise.promisifyAll(require('steem'));
steem.api.setOptions({ url: 'https://api.steemit.com'});

for(var i = 0; i < 10000; i++) {
 steem.api.getBlock(i, function(err, result) {
  if(result && result.transactions && result.transactions.length > 0) { 
   console.log(result.transactions);
  }
 });
}

steem.api.setBlockAppliedCallback(1, function(err, result) {
  console.log(err, result);
});

