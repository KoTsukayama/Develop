# train_model.py
import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split
import joblib

# ダミーデータの作成
data = {
    'sets': [3, 4, 5, 3, 4, 5, 3],
    'reps': [10, 8, 6, 12, 10, 8, 6],
    'weight': [40, 50, 60, 45, 55, 65, 70],
    'strength': [100, 150, 200, 110, 160, 210, 220]  # 予測したいターゲット
}
df = pd.DataFrame(data)

# 特徴量とターゲットの分離
X = df[['sets', 'reps', 'weight']]
y = df['strength']

# データの分割
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# スケーリング
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

# モデルのトレーニング
model = LinearRegression()
model.fit(X_train_scaled, y_train)

# モデルの評価
predictions = model.predict(X_test_scaled)
mse = mean_squared_error(y_test, predictions)
print(f"Mean Squared Error: {mse}")

# モデルとスケーラーの保存
joblib.dump(model, 'model/model.pkl')
joblib.dump(scaler, 'model/scaler.pkl')
