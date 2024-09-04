from flask import Flask, request, jsonify, render_template
import numpy as np

app = Flask(__name__)

# 仮のデータストレージ
workouts = []

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/add_workout', methods=['POST'])
def add_workout():
    data = request.json
    # 同じ日付のデータが存在するか確認
    existing_workout = next((workout for workout in workouts if workout['date'] == data['date']), None)

    if existing_workout:
        # データが存在する場合は更新
        existing_workout['weight'] = data['weight']
        message = "Workout updated successfully!"
    else:
        # 新しいデータとして追加
        workouts.append(data)
        message = "Workout added successfully!"

    return jsonify({"message": message})

@app.route('/progress', methods=['GET'])
def progress():
    if not workouts:
        return jsonify({"message": "No workouts recorded yet!"})

    weights = [workout['weight'] for workout in workouts]
    dates = [workout['date'] for workout in workouts]  # 日付も取得する
    mean_weight = np.mean(weights)
    mean_weight_rounded = round(mean_weight)  # 四捨五入して整数にする
    
    return jsonify({
        "average_weight": mean_weight_rounded,
        "weights": weights,
        "dates": dates
    })

if __name__ == '__main__':
    app.run(debug=True)
