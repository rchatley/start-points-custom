
ruby -c countdown.rb
if [ $? != 0 ]; then 
  echo 
  echo ">>> Score = 0"
  echo ">>> [countdown.rb is not a valid Ruby program]"
  echo
  exit
fi

ruby scorer.rb countdown.rb