from steem.steemd import Steemd
from steem import Steem
from steem.instance import set_shared_steemd_instance
from steem.account import Account
from steembase.account import PrivateKey
from steembase.exceptions import AccountDoesNotExistsException 
nodes = ['http://steemd.pevo.science']
custom_instance = Steemd(nodes=nodes)
set_shared_steemd_instance(custom_instance)

s = Steem(nodes)
w = s.wallet

file = open("k", "r") 
for line in file: 
 account_values = line.split(",")
 account = account_values[1].strip()
 key = account_values[0].strip()
 # print("key("+key+"),account("+account+")")
 try:
   acc = Account(account)
   privkey = PrivateKey(key)
   pubkey = privkey.pubkey
   print(account+","+str(pubkey)+","+str(privkey)+","+str(w.getKeyType(acc,str(pubkey))));
 except AccountDoesNotExistsException:
   print("account " + account +" does not exists")
