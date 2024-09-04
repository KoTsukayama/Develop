from flask import Flask, render_template, request, jsonify
import openai

app = Flask(__name__)

# OpenAI APIキーを設定
openai.api_key = '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'  # APIキーは安全な方法で管理してください

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/chat', methods=['POST'])
def chat():
    user_input = request.json.get("message")
    if user_input:
        response = generate_response(user_input)
        return jsonify({"response": response})
    else:
        return jsonify({"response": "No message received"}), 400

def generate_response(message):
    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "user", "content": message}
            ]
        )
        return response.choices[0].message['content'].strip()
    except openai.error.RateLimitError as e:
        return "Error: Rate limit exceeded. Please try again later."
    except openai.error.InvalidRequestError as e:
        return "Error: Invalid request. Please check your input."
    except openai.error.OpenAIError as e:
        return f"OpenAI API error: {e}"
    except Exception as e:
        return f"General error: {e}"


if __name__ == '__main__':
    app.run(debug=True)
