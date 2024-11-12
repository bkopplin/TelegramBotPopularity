import pandas as pd

df = pd.read_csv("../../../data/tg_bot_apis.csv")

sorted_by_stars = df.sort_values(by=["stars"], ascending=False)

print("Sorted by stars")
print(sorted_by_stars[1:5].to_string(index=None, columns=sorted_by_stars.columns))

sorted_by_users = df.sort_values(by=["usedBy"], ascending=False)

print("Sorted by users")
print(sorted_by_users[1:5].to_string(index=None, columns=sorted_by_users.columns))
