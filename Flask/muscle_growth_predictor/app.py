from flask import Flask, render_template, request, jsonify
from model import load_model, predict_muscle_growth

app = Flask(__name__)

# モデルのロード
model = load_model()

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    sets = data.get('sets')
    reps = data.get('reps')
    weight = data.get('weight')
    # トレーニングデータを使って筋肉成長を予測
    prediction = predict_muscle_growth(model, sets, reps, weight)
    return jsonify({'predicted_growth': prediction})

if __name__ == '__main__':
    app.run(debug=True)
