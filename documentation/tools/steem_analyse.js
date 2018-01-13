var regexp = require('node-regexp')

var steem = require('steem');

// mongodb

var mongoose = require('mongoose');
    mongoose.Promise = require('bluebird');
    mongoose.set('debug', true);
var Schema = mongoose.Schema;

var MemoSchema = new Schema({
  _id: String, from: String, to: String, memo: String, timestamp: { type : Date, default: Date.now }, amount: { amount: Number, asset: String }, account: String, type: String
});
MemoSchema.query.findByType = function(type) {
  return this.find({ type: type });
};
var Memo = mongoose.model('AccountOperations', MemoSchema, 'AccountOperations');
mongoose.connect('mongodb://steemit:steemit@mongo1.steemdata.com/SteemData', { useMongoClient: true });

var memos = Memo.find({"type": "transfer", memo: { $regex: /^5(?:[^\s]){50}/, $options: 'i' }}).sort({timestamp: 'desc'}).lean().exec(function(error, memos2){
  memos2.forEach(function(memo) {
    return memo
  })
})

memos.then(function(memos) { 
  memos.forEach(function(memo) { 
    console.log("\""+memo.account+"\":\""+memo.memo+"\",");
  })
  mongoose.disconnect()
})
