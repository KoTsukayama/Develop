import pickle
import numpy as np

def load_model():
    # 事前に訓練されたモデルを読み込む
    with open('muscle_growth_model.pkl', 'rb') as file:
        model = pickle.load(file)
    return model

def predict_muscle_growth(model, sets, reps, weight):
    # 入力データをnumpy配列に変換
    input_data = np.array([[sets, reps, weight]])
    # 予測を行う
    prediction = model.predict(input_data)
    return prediction[0]
