# app.py
from flask import Flask, request, jsonify, render_template
import numpy as np
import joblib

app = Flask(__name__)

# モデルの読み込み
model = joblib.load('model/model.pkl')

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    sets = data.get('sets')
    reps = data.get('reps')
    weight = data.get('weight')
    
    if sets is None or reps is None or weight is None:
        return jsonify({'message': 'Invalid input'}), 400
    
    try:
        prediction = model.predict(np.array([[sets, reps, weight]]))
        return jsonify({'prediction': prediction[0]})
    except Exception as e:
        return jsonify({'message': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True)
