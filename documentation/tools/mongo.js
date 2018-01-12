var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var MemoSchema = new Schema({
_id: String, from: String, to: String, memo: String, timestamp: { type : Date, default: Date.now }, account: String, type: String
});
MemoSchema.query.byType = function(type) {
  return this.find({ type: new RegExp(type, 'i') });
};

var Memo = mongoose.model('AccountOperations', MemoSchema, 'AccountOperations');
mongoose.connect('mongodb://steemit:steemit@mongo1.steemdata.com/SteemData', { useMongoClient: true });
Memo.find().byType('transfer').limit(20).exec(function (err, memo) {
  if (err) return console.error(err);
  console.log(memo);
})
