from flask import Flask, request, jsonify, render_template
import pandas as pd
import numpy as np

app = Flask(__name__)

# データの初期化
data_file = 'data/training_data.csv'

def load_data():
    try:
        return pd.read_csv(data_file)
    except FileNotFoundError:
        return pd.DataFrame(columns=['Date', 'Sets', 'Reps', 'Weight', 'TrainingLoad'])

def save_data(df):
    df.to_csv(data_file, index=False)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/add_entry', methods=['POST'])
def add_entry():
    data = request.json
    date = data.get('date')  # 受け取った日付データ
    sets = int(data.get('sets'))
    reps = int(data.get('reps'))
    weight = float(data.get('weight'))
    
    # トレーニング負荷の計算
    training_load = sets * reps * weight

    # データの保存
    df = load_data()
    new_entry = {'Date': date, 'Sets': sets, 'Reps': reps, 'Weight': weight, 'TrainingLoad': training_load}
    df = pd.concat([df, pd.DataFrame([new_entry])], ignore_index=True)
    save_data(df)

    return jsonify({'message': 'Entry added successfully!'})

@app.route('/training_history')
def training_history():
    df = load_data()
    if df.empty:
        return jsonify({'dates': [], 'loads': [], 'sets': [], 'reps': [], 'weights': []})

    return jsonify({
        'dates': df['Date'].tolist(),
        'loads': df['TrainingLoad'].tolist(),
        'sets': df['Sets'].tolist(),
        'reps': df['Reps'].tolist(),
        'weights': df['Weight'].tolist()
    })

if __name__ == '__main__':
    app.run(debug=True)
