module Problems() where

problem1 :: Int
problem1 = sum $ filter (\n -> mod n 3 == 0 || mod n 5 == 0) $ [1..1000]

problem2 :: Int
problem2 = 
    sum $ filter even $ takeWhile (< 4000000) fibList
    where fibList = [0, 1] ++ zipWith (+) fibList (tail fibList)
